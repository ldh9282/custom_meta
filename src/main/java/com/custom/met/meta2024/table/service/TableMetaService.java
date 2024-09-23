package com.custom.met.meta2024.table.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.custom.met.cmmn.exception.CustomException;
import com.custom.met.cmmn.exception.CustomExceptionCode;
import com.custom.met.cmmn.model.CustomMap;
import com.custom.met.cmmn.security.utils.SecurityUtils;
import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.meta2024.column.service.ColumnMetaDao;
import com.custom.met.meta2024.seq.service.SeqMetaDao;

import lombok.extern.log4j.Log4j2;

@Service @Log4j2
public class TableMetaService {

	@Autowired
	private TableMetaDao tableMetaDao;
	
	@Autowired
	private ColumnMetaDao columnMetaDao;
	
	@Autowired
	private SeqMetaDao seqMetaDao;
	
	/**
	 * <pre>
	 * 메서드명: getTableMetaDetail
	 * 설명: 테이블메타 상세조회
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	public CustomMap getTableMetaDetail(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		requestMap.put("tableMetaSno", customMap.getString("tableMetaSno"));
		
		try {
			CustomMap tableMetaInfo = tableMetaDao.selectTableMeta(requestMap);
			List<CustomMap> tableColumnList = tableMetaDao.selectTableColumnList(requestMap);
			
			
			resultMap.put("tableMetaInfo", tableMetaInfo);
			resultMap.put("tableColumnList", tableColumnList);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "테이블메타 상세조회" }, e);
		}
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 메서드명: insertTableMetaInfo
	 * 설명: 테이블메타정보 등록
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@Transactional
	public CustomMap insertTableMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		List<CustomMap> pkColumnList = customMap.getCustomMapList("pkColumnList");
		List<CustomMap> columnList = customMap.getCustomMapList("columnList");
		
		
		try {
			CustomMap tableMap = new CustomMap();
			
			tableMap.put("schemaName", customMap.getString("schemaName"));
			tableMap.put("tableName", customMap.getString("tableName"));
			tableMap.put("tableDesc", customMap.getString("tableDesc"));
			
			tableMap.put("pkColumnList", pkColumnList);
			
			tableMap.put("columnList", columnList);
			
			tableMetaDao.createTable(tableMap);
			
			tableMetaDao.addTableComment(tableMap);
			
			String tableMetaSno = tableMetaDao.selectTableMetaSno();
			
			requestMap.put("tableMetaSno", tableMetaSno);
			requestMap.put("schemaName", customMap.getString("schemaName"));
			requestMap.put("tableName", customMap.getString("tableName"));
			requestMap.put("tableDesc", customMap.getString("tableDesc"));
			requestMap.put("sysCreator", "SYSTEM");
			
			tableMetaDao.insertTableMeta(requestMap);
			
			for (CustomMap item : pkColumnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				requestMap.put("columnMetaSno", columnMetaSno);
				requestMap.put("columnName", item.getString("columnName"));
				requestMap.put("columnCamelName", item.getString("columnCamelName"));
				requestMap.put("columnSnakeName", item.getString("columnSnakeName"));
				requestMap.put("columnType", item.getString("columnType"));
				requestMap.put("termSno", item.getString("termSno"));
				requestMap.put("nullColumnYn", item.getString("nullColumnYn"));
				requestMap.put("pkColumnYn", item.getString("pkColumnYn"));
				requestMap.put("sysColumnYn", item.getString("sysColumnYn"));
				
				
				columnMetaDao.insertColumnMeta(requestMap);
			}
			for (CustomMap item : columnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				requestMap.put("columnMetaSno", columnMetaSno);
				requestMap.put("columnName", item.getString("columnName"));
				requestMap.put("columnCamelName", item.getString("columnCamelName"));
				requestMap.put("columnSnakeName", item.getString("columnSnakeName"));
				requestMap.put("columnType", item.getString("columnType"));
				requestMap.put("termSno", item.getString("termSno"));
				requestMap.put("nullColumnYn", item.getString("nullColumnYn"));
				requestMap.put("pkColumnYn", item.getString("pkColumnYn"));
				requestMap.put("sysColumnYn", item.getString("sysColumnYn"));
				
				
				columnMetaDao.insertColumnMeta(requestMap);
			}
			
			List<CustomMap> sysColumnList = Arrays.asList(
					createSysColumnMap("시스템생성자", "sysCreator", "SYS_CREATOR", "CHARACTER VARYING(200)"),
					createSysColumnMap("시스템수정자", "sysModifier", "SYS_MODIFIER", "CHARACTER VARYING(200)"),
					createSysColumnMap("시스템생성일", "sysCreatedAt", "SYS_CREATED_AT", "TIMESTAMP WITHOUT TIME ZONE"),
					createSysColumnMap("시스템수정일", "sysModifiedAt", "SYS_MODIFIED_AT", "TIMESTAMP WITHOUT TIME ZONE")
					);
			for (CustomMap item : sysColumnList) {
				String columnMetaSno = columnMetaDao.selectColumnMetaSno();
				requestMap.put("columnMetaSno", columnMetaSno);
				requestMap.put("columnName", item.getString("columnName"));
				requestMap.put("columnCamelName", item.getString("columnCamelName"));
				requestMap.put("columnSnakeName", item.getString("columnSnakeName"));
				requestMap.put("columnType", item.getString("columnType"));
				requestMap.put("termSno", item.getString("termSno"));
				requestMap.put("nullColumnYn", item.getString("nullColumnYn"));
				requestMap.put("pkColumnYn", item.getString("pkColumnYn"));
				requestMap.put("sysColumnYn", item.getString("sysColumnYn"));
				
				
				columnMetaDao.insertColumnMeta(requestMap);
			}
			
			tableMetaDao.createPkIndex(tableMap);
			
			seqMetaDao.createSeq(tableMap);
			
			String seqMetaSno = seqMetaDao.selectSeqMetaSno();
			
			requestMap.put("seqMetaSno", seqMetaSno);
			
			seqMetaDao.insertSeqMeta(requestMap);
			
			
			
			
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new CustomException(CustomExceptionCode.ERR521, new String[] {"테이블메타기본정보"}, e);
		}
		
		return resultMap;
		
	}
	
	
	/**
	 * <pre>
	 * 메서드명: getSchemaNameList
	 * 설명: 스키마명 정보조회
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException 
	 */
	public CustomMap getSchemaNameInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		try {
			
			List<CustomMap> schemaNameList = tableMetaDao.selectSchemaNameList(customMap);
			resultMap.put("list", schemaNameList);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"스키마명 정보"}, e);
		}
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 메서드명: createSysColumnMap
	 * 설명: 시스템컬럼맵 생성
	 * </pre>
	 * @param columnName
	 * @param columnCamelName
	 * @param columnSnakeName
	 * @return
	 */
	private CustomMap createSysColumnMap(String columnName, String columnCamelName, String columnSnakeName, String columnType) {
	    CustomMap column = new CustomMap();
	    column.put("columnName", columnName);
	    column.put("columnCamelName", columnCamelName);
	    column.put("columnSnakeName", columnSnakeName);
	    column.put("columnType", columnType);
	    column.put("termSno", "");
	    column.put("nullColumnYn", "0");
	    column.put("pkColumnYn", "0");
	    column.put("sysColumnYn", "1");
	    return column;
	}

	/**
	 * <pre>
	 * 메서드명: getTableMetaInfoList
	 * 설명: 테이블메타정보 목록조회
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	public CustomMap getTableMetaInfoList(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			customMap.put("pageNum", customMap.getString("pageNum", "1"));
			customMap.put("rowAmountPerPage", customMap.getString("rowAmountPerPage", "10"));
			
			List<CustomMap> tableMetaInfoList = tableMetaDao.selectTableMetaInfoList(customMap);
			
			resultMap.put("tableMetaInfoList", tableMetaInfoList);
			resultMap.put("count", tableMetaInfoList.size() > 0 ? tableMetaInfoList.get(0).getString("count") : "0");
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] {"테이블메타정보목록"}, e);
		}
		
		
		return resultMap;
	}

	/**
	 * <pre>
	 * 메서드명: deleteTableMetaInfo
	 * 설명: 테이블메타정보 삭제
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@Transactional
	public CustomMap deleteTableMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		try {
			/**
			 * 1. 시퀀스메타기본정보조회
			 */
			CustomMap seqMetaInfoDetail = seqMetaDao.selectSeqMetaInfoDetail(customMap);
			customMap.put("schemaName", seqMetaInfoDetail.getString("schemaName"));
			customMap.put("seqName", seqMetaInfoDetail.getString("seqName"));
			
			/**
			 * 2. 시퀀스삭제 및 시퀀스메타기본정보삭제
			 */
			seqMetaDao.dropSeq(customMap);
			seqMetaDao.deleteSeqMeta(customMap);
			
			/**
			 * 3. 컬럼메타기본정보삭제
			 */
			columnMetaDao.deleteColumnMetaInfo(customMap);
			
			/**
			 * 4. 테이블메타기본정보 상세조회
			 */
			CustomMap tableMeta = tableMetaDao.selectTableMeta(customMap);
			customMap.put("schemaName", tableMeta.getString("schemaName"));
			customMap.put("tableName", tableMeta.getString("tableName"));
			/**
			 * 5. 테이블삭제 및 테이블메타기본정보삭제
			 */
			tableMetaDao.dropTable(customMap);
			tableMetaDao.deleteTableMetaInfo(customMap);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] {"테이블메타정보"}, e);
		}
		
		return resultMap;
	}

	/**
	 * <pre>
	 * 메서드명: updateTableMetaInfo
	 * 설명: 테이블메타정보 수정
	 * </pre>
	 * @param customMap
	 * @return
	 * @throws CustomException
	 */
	@Transactional
	public CustomMap updateTableMetaInfo(CustomMap customMap) throws CustomException {
		CustomMap resultMap = new CustomMap();
		
		CustomMap requestMap = new CustomMap();
		
		requestMap.put("tableMetaSno", customMap.getString("tableMetaSno"));
		requestMap.put("tableDesc", customMap.getString("tableDesc"));
		List<CustomMap> pkColumnList = customMap.getCustomMapList("pkColumnList");
		List<CustomMap> columnList = customMap.getCustomMapList("columnList");
		requestMap.put("sysModifier", StringUtils.NVL(SecurityUtils.getUsername(), "SYSTEM"));
		
		try {
			CustomMap tableMeta = tableMetaDao.selectTableMeta(requestMap);
			requestMap.put("schemaName", tableMeta.getString("schemaName"));
			requestMap.put("tableName", tableMeta.getString("tableName"));
			for (CustomMap item : pkColumnList) {
				requestMap.put("columnMetaSno", item.getString("columnMetaSno"));
				requestMap.put("columnName", item.getString("columnName"));
				requestMap.put("columnCamelName", item.getString("columnCamelName"));
				requestMap.put("columnSnakeName", item.getString("columnSnakeName"));
				requestMap.put("columnType", item.getString("columnType"));
				requestMap.put("termSno", item.getString("termSno"));
				requestMap.put("nullColumnYn", item.getString("nullColumnYn"));
				requestMap.put("pkColumnYn", item.getString("pkColumnYn"));
				requestMap.put("sysColumnYn", item.getString("sysColumnYn"));
				
				
				CustomMap columnMeta = columnMetaDao.selectColumnMetaInfo(requestMap);
				requestMap.put("oldColumnName", columnMeta.getString("columnName"));
				requestMap.put("oldColumnType", columnMeta.getString("columnType"));
				
				boolean isUpdateColumn = false;
				if (!requestMap.getString("oldColumnName").equals(requestMap.getString("columnName"))) {
					columnMetaDao.alterColumnName(requestMap);
					isUpdateColumn = true;
				}
				if (!requestMap.getString("oldColumnType").equals(requestMap.getString("columnType"))) {
					columnMetaDao.alterColumnType(requestMap);
					isUpdateColumn = true;
				}
				
				if (isUpdateColumn) {
					
					columnMetaDao.updateColumnMetaInfo(requestMap);
				}
				
			}
			for (CustomMap item : columnList) {
				requestMap.put("columnMetaSno", item.getString("columnMetaSno"));
				requestMap.put("columnName", item.getString("columnName"));
				requestMap.put("columnCamelName", item.getString("columnCamelName"));
				requestMap.put("columnSnakeName", item.getString("columnSnakeName"));
				requestMap.put("columnType", item.getString("columnType"));
				requestMap.put("termSno", item.getString("termSno"));
				requestMap.put("nullColumnYn", item.getString("nullColumnYn"));
				requestMap.put("pkColumnYn", item.getString("pkColumnYn"));
				requestMap.put("sysColumnYn", item.getString("sysColumnYn"));
				
				
				CustomMap columnMeta = columnMetaDao.selectColumnMetaInfo(requestMap);
				requestMap.put("oldColumnName", columnMeta.getString("columnName"));
				requestMap.put("oldColumnType", columnMeta.getString("columnType"));
				
				boolean isUpdateColumn = false;
				if (!requestMap.getString("oldColumnName").equals(requestMap.getString("columnName"))) {
					columnMetaDao.alterColumnName(requestMap);
					isUpdateColumn = true;
				}
				if (!requestMap.getString("oldColumnType").equals(requestMap.getString("columnType"))) {
					columnMetaDao.alterColumnType(requestMap);
					isUpdateColumn = true;
				}
				
				if (isUpdateColumn) {
					
					columnMetaDao.updateColumnMetaInfo(requestMap);
				}
				
			}
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] {"테이블메타정보"}, e);
		}
		
		return resultMap;
	}
}
