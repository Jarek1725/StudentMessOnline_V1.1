
xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        addToIndexedDB(this.responseText)
    }
};

xhttp.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/studentLoggedIn", true);
xhttp.send();


function addToIndexedDB(user){
        let db;
        let dbReq = indexedDB.open('myDatabase', 1);
        dbReq.onupgradeneeded = function(event) {
            db = event.target.result;
            let notes = db.createObjectStore('user', {autoIncrement: true});
        }
        dbReq.onsuccess = function(event) {
            db = event.target.result;
            addStickyNote(db, user)
        }
        dbReq.onerror = function(event) {
            alert('error opening database ' + event.target.errorCode);
        }
}



function addStickyNote(db, user) {
    // Start a database transaction and get the notes object store
    let tx = db.transaction(['user'], 'readwrite');
    let store = tx.objectStore('user');
    // Put the sticky note into the object store
    let note = user;
    // if(sessionStorage.length===0) {
        store.add(note);
    // }
    // Wait for the database transaction to complete
    tx.oncomplete = function() {
        getData(db)
    }
    tx.onerror = function(event) {
        alert('error storing note ' + event.target.errorCode);
    }
}

function getData(){

    let db;
    let dbReq = indexedDB.open('myDatabase', 1);

    dbReq.onsuccess = function(event) {
        db = event.target.result;
            let tx = db.transaction(['user'], 'readwrite');
            let store = tx.objectStore('user');
            let req = store.get(2);
        req.onsuccess = function(event) {
            let note = event.target.result;
            if (note) {
                sessionStorage.removeItem("user")
                sessionStorage.setItem("user", note)
            } else {
                window.location.reload();
                console.log("note 1 notasd found")
            }
        }
    }
}