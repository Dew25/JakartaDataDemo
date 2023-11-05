let userId = document.getElementById("userId");
let firstname = document.getElementById("firstname");
let lastname = document.getElementById("lastname");
let btnAdd = document.getElementById("btnAdd");
btnAdd.addEventListener('click',(e)=>{
    let data = {
        "userId":userId.value,
        "firstname":firstname.value,
        "lastname": lastname.value
    };
    fetch("api/user/add",{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    })
        .then(response =>response.json())
        .then(response=>{
            if(response !== null){
                let listUsers = document.getElementById("listUsers");
                let div = document.createElement("div");
                div.setAttribute("id",response.userId);
                div.addEventListener("click",e=>{
                    deleteUser(response.userId);
                });
                div.innerHTML=response.userId+'. '+response.firstname+' '+ response.lastname;
                listUsers.appendChild(div);
            }
        })
});
let btnList= document.getElementById("btnList");
btnList.addEventListener('click',(e)=>{
   fetch("api/user",{
       method: 'GET',
       headers: {
           'Content-Type': 'application/json;charset=utf-8'
       },
   })
       .then(response => response.json())
       .then(arrUsers =>{
           printListUsers(arrUsers);
       })
});
function printListUsers(arrUsers){
    let listUsers = document.getElementById("listUsers");
    listUsers.innerHTML='';
    for(let i = 0;i< arrUsers.length;i++){
        let div = document.createElement("div");
        div.setAttribute("id",arrUsers[i].userId);
        div.addEventListener("click",e=>{
            deleteUser(arrUsers[i].userId);
        });
        div.innerHTML=arrUsers[i].userId+'. '+arrUsers[i].firstname+' '+ arrUsers[i].lastname;
        listUsers.appendChild(div);
    }
}
function deleteUser(userId){
    fetch("api/user/"+userId,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).then(btnList.click());
}
