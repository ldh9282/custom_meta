<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html lang="en">

<head>
    <style>
        #content {
            width: 80%;
            max-width: 300px;
            height: 500px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        #btnSnake2Camel {
            float: right;
        }

        #btnResultChangeCase {
            float: right;
        }
    </style>
</head>

<body>

    <div id="content">
        <p>
            <textarea name="in" id="in" cols="40" rows="10"></textarea>
        </p>
        <p>
            <button type="button" id="btnCamel2Snake">camel2Snake</button>
            <button type="button" id="btnSnake2Camel">snake2Camel</button>
        </p>
        <p>
            <button type="button" id="btnChangeCase">changeCase ::: in</button>
            <button type="button" id="btnResultChangeCase">changeCase ::: out</button>
        </p>
        <p>
            <textarea name="out" id="out" cols="40" rows="10"></textarea>
        </p>
    </div>

    <script>
        document.querySelector('#btnCamel2Snake').addEventListener('click', () => {
            let input = document.querySelector('#in').value;
            document.querySelector('#out').value = camel2Snake(input);
        });
        document.querySelector('#btnSnake2Camel').addEventListener('click', () => {
            let input = document.querySelector('#in').value;
            document.querySelector('#out').value = snake2Camel(input);
        });
        document.querySelector('#btnChangeCase').addEventListener('click', () => {
            let input = document.querySelector('#in').value;
            document.querySelector('#in').value = chanegeCase(input);
        });
        document.querySelector('#btnResultChangeCase').addEventListener('click', () => {
            let input = document.querySelector('#out').value;
            document.querySelector('#out').value = chanegeCase(input);
        });

        function camel2Snake(str) {
            str = str.charAt(0).toLowerCase() + str.substr(1, str.length);
            return str.replace(/([A-Z])/g, (word) => '_' + word.toLowerCase()).toUpperCase();
        }
        function snake2Camel(str) {
            str = str.toLowerCase();
            return str.replace(/_./g, (word) => word.charAt(1).toUpperCase());
        }
        function changeCase(str) {
            if (str === str.toLowerCase()) {
                return str.toUpperCase();
            } else if (str === str.toUpperCase()) {
                return str.toLowerCase();
            } else {
                return str;
            }
        }
    </script>

</body>

</html>