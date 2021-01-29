let DBDeleteRequest = window.indexedDB.deleteDatabase("myDatabase");

DBDeleteRequest.onerror = function(event) {
    console.log("Error deleting database.");
};

DBDeleteRequest.onsuccess = function(event) {
    console.log("Database deleted successfully");

    console.log(event.result); // should be undefined
};