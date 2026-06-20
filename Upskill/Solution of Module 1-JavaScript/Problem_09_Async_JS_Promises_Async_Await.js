// Mock JSON data endpoint representation
const mockEventsData = [
    { title: "Music Festival", seats: 12 },
    { title: "Art Expo", seats: 5 }
];

// Async function using Promise with timer simulation
function fetchEventsMock() {
    return new Promise((resolve, reject) => {
        console.log("Loading spinner shown...");
        setTimeout(() => {
            resolve(mockEventsData);
        }, 1500);
    });
}

// 1. Handling using .then() and .catch()
console.log("Testing .then() promise chain:");
fetchEventsMock()
    .then(data => {
        console.log("Data loaded via .then():", data);
        console.log("Loading spinner hidden.");
    })
    .catch(err => console.error(err));

// 2. Handling using async/await
async function loadEventsAsync() {
    console.log("\nTesting async/await:");
    try {
        const data = await fetchEventsMock();
        console.log("Data loaded via async/await:", data);
        console.log("Loading spinner hidden.");
    } catch (err) {
        console.error(err);
    }
}

// Trigger async test after the first one resolves
setTimeout(loadEventsAsync, 2000);