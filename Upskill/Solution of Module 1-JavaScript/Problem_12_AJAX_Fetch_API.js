// Mock API endpoint URL
const postUrl = "https://jsonplaceholder.typicode.com/posts";

function registerUserToServer(userData) {
    console.log("Sending registration request...");
    
    // Use setTimeout to simulate delay
    setTimeout(() => {
        // Use fetch() to POST user data to a mock API
        fetch(postUrl, {
            method: 'POST',
            body: JSON.stringify(userData),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
        .then((json) => {
            // Show success message
            console.log("Success: Registration saved on server:", json);
        })
        .catch((err) => {
            // Show failure message
            console.error("Failure: Server registration failed:", err.message);
        });
    }, 1000);
}

// Trigger registration
registerUserToServer({ name: "Charlie", email: "charlie@test.com", event: "Music Fest" });