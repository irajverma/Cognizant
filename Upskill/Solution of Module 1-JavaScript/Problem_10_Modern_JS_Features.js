// Modern JavaScript Features: let, const, default parameters, destructuring, spread

// 1. Default Parameters
function createRegistration(userName, eventTitle = "General Gathering") {
    console.log(`Registering ${userName} for event: ${eventTitle}`);
}
createRegistration("Alice");
createRegistration("Bob", "Jazz Concert");

// 2. Destructuring
const eventDetails = {
    title: "Art Gallery",
    date: "2026-08-20",
    location: "City Center",
    organizer: "City Council"
};
const { title, date } = eventDetails;
console.log(`Destructured Title: ${title}, Date: ${date}`);

// 3. Spread Operator
const baseEventList = ["Food Truck Fest", "Coding Seminar"];
// Spread operator to clone and add a new event before filtering
const updatedEventList = [...baseEventList, "Music Show"];
console.log("Cloned and Expanded List via Spread:", updatedEventList);