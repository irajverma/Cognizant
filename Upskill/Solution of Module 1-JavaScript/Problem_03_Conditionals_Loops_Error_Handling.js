const events = [
    { name: "Music Festival", date: "2026-07-15", seats: 12, past: false },
    { name: "Old Art Expo", date: "2025-12-01", seats: 5, past: true },
    { name: "Sold Out Sports Meet", date: "2026-08-10", seats: 0, past: false }
];

console.log("Filtering upcoming events with available seats:");

// Loop through the event list using forEach()
events.forEach(event => {
    // If-else condition to filter out past or full events
    if (event.past) {
        console.log(`- ${event.name} is a past event (hidden).`);
    } else if (event.seats <= 0) {
        console.log(`- ${event.name} is fully booked (hidden).`);
    } else {
        console.log(`- ACTIVE EVENT: ${event.name} on ${event.date} (${event.seats} seats available).`);
    }
});

// Wrap registration logic in try-catch to handle errors
function registerUserForEvent(event, user) {
    try {
        if (!user.name || !user.email) {
            throw new Error("Invalid User: Missing name or email.");
        }
        if (event.seats <= 0) {
            throw new Error(`Registration Failed: Event "${event.name}" is sold out.`);
        }
        event.seats--;
        console.log(`Success: Registered ${user.name} for "${event.name}". Remaining seats: ${event.seats}`);
    } catch (error) {
        console.error(`Error encountered: ${error.message}`);
    }
}

// Test try-catch block
registerUserForEvent(events[2], { name: "Bob", email: "bob@test.com" }); // Will throw sold out error
registerUserForEvent(events[0], { name: "Alice" }); // Will throw invalid user error
registerUserForEvent(events[0], { name: "Alice", email: "alice@test.com" }); // Should succeed