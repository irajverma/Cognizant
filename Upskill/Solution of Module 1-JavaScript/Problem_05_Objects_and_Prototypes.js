// Define Event constructor/class
class Event {
    constructor(title, seats, city) {
        this.title = title;
        this.seats = seats;
        this.city = city;
    }
}

// Add checkAvailability() to prototype
Event.prototype.checkAvailability = function() {
    return this.seats > 0;
};

// Create instances
const event1 = new Event("Tech Summit", 15, "New York");
const event2 = new Event("Sold Out Expo", 0, "Chicago");

console.log(`${event1.title} available?`, event1.checkAvailability());
console.log(`${event2.title} available?`, event2.checkAvailability());

// List object keys and values using Object.entries()
console.log("Listing details of Event 1:");
for (const [key, value] of Object.entries(event1)) {
    console.log(`${key}: ${value}`);
}