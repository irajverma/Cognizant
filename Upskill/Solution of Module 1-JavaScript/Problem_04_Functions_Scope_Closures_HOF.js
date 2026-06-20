const eventList = [];

// Reusable functions
function addEvent(name, category, seats) {
    const newEvent = { name, category, seats };
    eventList.push(newEvent);
    return newEvent;
}

function registerUser(event) {
    if (event.seats > 0) {
        event.seats--;
        return true;
    }
    return false;
}

// Higher-order function: accepts a callback filter function
function filterEvents(list, callback) {
    const results = [];
    for (let i = 0; i < list.length; i++) {
        if (callback(list[i])) {
            results.push(list[i]);
        }
    }
    return results;
}

// Closure function to track total registrations for a category
function createRegistrationTracker(category) {
    let registrationCount = 0; // Lexical scope variable
    return function() {
        registrationCount++;
        return `Category [${category}] has ${registrationCount} registrations.`;
    };
}

// Populate events
addEvent("Rock Concert", "Music", 50);
addEvent("Jazz Night", "Music", 20);
addEvent("Art Expo", "Arts", 30);

// Tracker test
const musicTracker = createRegistrationTracker("Music");
console.log(musicTracker()); // "Category [Music] has 1 registrations."
console.log(musicTracker()); // "Category [Music] has 2 registrations."

// HOF Filter test
const musicEvents = filterEvents(eventList, (evt) => evt.category === "Music");
console.log("Filtered Music Events:", musicEvents);