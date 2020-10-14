const url = 'http://localhost:8080/';
let stompClient;
let selectedUser;

function connectToChat(userName) {
    console.log('Connecting to chat...');
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        console.log("Connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, (response) => {
            let data = JSON.parse(response.body);
            render(data.message, data.sender)
        })
    })
}

function sendMsg(from, message) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        sender: from,
        message: message
    }))
}

function registration() {
    let userName = document.getElementById("userName").value;
    $.get(url+"registration/"+userName, function(response) {
        connectToChat(userName);
    }).fail(function(error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function fetchAll() {
    $.get(url+"fetchAllUsers", function(response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i=0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\''+users[i]+'\')"><li class="clearfix">\n' +
                '                <img src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png" width="55px" height="55px" alt="avatar" />\n' +
                '                <div class="about">\n' +
                '                    <div class="name">' + users[i] + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>'
        }
        $('#usersList').html(usersTemplateHTML)
    })
}

function selectUser(userName) {
    console.log("slecting users: " + userName)
    selectedUser = userName;
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}