<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SQL to Excel Converter</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.2/xlsx.full.min.js"></script>
</head>

<body>
	<h1>SQL to Excel Converter</h1>
    <textarea id="sqlTextarea" rows="10" cols="100"></textarea><br>
    <button onclick="previewData()">Preview</button>
    <button onclick="generateExcel()">Generate Excel</button>
    <div id="previewTable"></div>
    <script>
        function convertToCamelCase(snake_str) {
            const components = snake_str.split('_');
            return components[0].toLowerCase() + components.slice(1).map(x => x.charAt(0).toUpperCase() + x.slice(1).toLowerCase()).join('');
        }

        function processLine(line) {
            const regex = / AS /gi;
            const regexKr = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;
            const columns = [];
            let match;
            while ((match = regex.exec(line)) !== null) {
                let columnName = match.input.split(regex)[0].trim();
                let columnNameKr = '';
                for (let i = 0; i < columnName.length; i++) {
                    const ch = columnName.charAt(i);
                    if (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(ch)) {
                        columnNameKr += ch;
                    }
                }
                columnName = columnNameKr;

                let aliasName = match.input.split(regex)[1].trim();

                columns.push({
                    columnName: columnName,
                    alias: aliasName,
                    camelCase: convertToCamelCase(aliasName)
                });
            }
            return columns;
        }

        function extractColumns(sqlQuery) {
            const lines = sqlQuery.split('\n');
            let columns = [];
            lines.forEach(line => {
                line = line.replaceAll('\t', '').replaceAll(',', '');
                columns = columns.concat(processLine(line));
            });
            console.log(`Extracted columns: ${JSON.stringify(columns)}`);
            return columns;
        }

        function previewData() {
            const sqlQuery = document.getElementById('sqlTextarea').value;
            const columns = extractColumns(sqlQuery);

            const previewTable = document.getElementById('previewTable');
            previewTable.innerHTML = '';

            if (columns.length > 0) {
                const table = document.createElement('table');
                table.border = '1';

                const headerRow = table.insertRow();
                ['columnName', 'columnCamelName', 'columnSnakeName'].forEach(text => {
                    const cell = document.createElement('th');
                    cell.textContent = text;
                    headerRow.appendChild(cell);
                });

                columns.forEach(col => {
                    const row = table.insertRow();
                    row.insertCell().textContent = col.columnName;
                    row.insertCell().textContent = col.camelCase;
                    row.insertCell().textContent = col.alias;
                });

                previewTable.appendChild(table);
            } else {
                previewTable.textContent = 'No columns found.';
            }
        }

        function generateExcel() {
            const sqlQuery = document.getElementById('sqlTextarea').value;
            const columns = extractColumns(sqlQuery);

            const worksheetData = columns.map(col => [
                col.columnName,
                col.camelCase,
                col.alias
            ]);

            const worksheet = XLSX.utils.aoa_to_sheet([
                ['columnName', 'columnCamelName', 'columnSnakeName'],
                ...worksheetData
            ]);

            const workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, worksheet, 'Columns');

            XLSX.writeFile(workbook, 'columns.xlsx');
        }





    </script>
</body>
</html>    