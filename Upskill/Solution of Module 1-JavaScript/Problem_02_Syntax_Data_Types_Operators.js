// Event details using const (constant values)
const eventName = "Summer Harmony Concert";
const eventDate = "2026-07-15";

// Available seats using let (value can change)
let availableSeats = 100;

// Concatenating event details using template literals
console.log(`Event Name: ${eventName}`);
console.log(`Event Date: ${eventDate}`);
console.log(`Initial Seats: ${availableSeats}`);

// Simulate seat registration (decrement seat count)
availableSeats--;
console.log(`Seat registered! Remaining seats: ${availableSeats}`);

// Simulate cancellation (increment seat count)
availableSeats++;
console.log(`Registration cancelled! Restored seats: ${availableSeats}`);