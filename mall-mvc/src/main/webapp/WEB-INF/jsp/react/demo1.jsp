<%--
  Created by IntelliJ IDEA.
  User: mayn
  Date: 2019/6/29
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>React Demo1</title>
</head>
<body>


<div id="example"></div>


<script type="text/babel">

    class Toggle extends React.Component {
        constructor(props) {
            super(props);
            this.state = {isToggleOn: true};

            // 这边绑定是必要的，这样 `this` 才能在回调函数中使用
            this.handleClick = this.handleClick.bind(this);
        }

        handleClick() {

            let newState = {
                isToggleOn: !this.state.isToggleOn
            };

            this.setState(newState);
        }

        render() {
            return (
                <button onClick={this.handleClick}>
                    {this.state.isToggleOn ? 'ON' : 'OFF'}
                </button>
            );
        }
    }

    ReactDOM.render(
        <Toggle />,
        document.getElementById('example')
    );

</script>

<%--
<script src="https://unpkg.com/react@16/umd/react.development.js"></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
<!-- 生产环境中不建议使用 -->
<script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
--%>




<script src="https://cdn.staticfile.org/react/15.4.2/react.min.js"></script>
<script src="https://cdn.staticfile.org/react/15.4.2/react-dom.min.js"></script>
<script src="https://cdn.staticfile.org/babel-standalone/6.22.1/babel.min.js"></script>

</body>
</html>
