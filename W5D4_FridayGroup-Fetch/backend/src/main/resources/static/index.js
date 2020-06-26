const buttonContainer = document.getElementById("delButton");


//getData liest die Datenbank aus und gibt sie auf der Seite aus

async function getData() {
  const response = await fetch("api/todo");
  const data = await response.json();
  console.log(data);

  buttonContainer.innerHTML = data.map(todo => `${todo.description} ${todo.status}
<div id="delButton">
<button onclick="deleteToDo('${todo.id}')">DELETE</button>
<button onclick="changeToDoStatus('${todo.id}, IN_PROGRESS')">IN PROGRESS</button>
<button onclick="changeToDoStatus('${todo.id}, DONE')">DONE</button>
</div><br>`).join("")


}

getData().then(() => console.log("Fetch data done"))

async function addNewToDo() {
  const todoElement = document.getElementById("addToDo");
  const toDoToAdd = {
    description: todoElement.value
  };
  await fetch("api/todo", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(toDoToAdd)

  })
  todoElement.value = "";
  await getData();
}

async function deleteToDo(id) {

  await fetch("http://localhost:8080/api/todo/" + id, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json"
    },


  })
  await getData();
}

async function changeToDoStatus(id,status) {
  await fetch("http://localhost:8080/api/todo/" + id + status, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },


  })

  await getData();

}
