const events = [
    { title: "Rock Festival", category: "Music", fee: 25 },
    { title: "Baking Workshop", category: "Food", fee: 15 },
    { title: "Jazz Concert", category: "Music", fee: 20 }
];

console.log("Initial Event List:", events);

// Add new events using .push()
events.push({ title: "Craft Exhibition", category: "Art", fee: 10 });
console.log("After pushing new event:", events);

// Use .filter() to show only Music events
const musicEvents = events.filter(evt => evt.category === "Music");
console.log("Only Music Events (Filtered):", musicEvents);

// Use .map() to format display card titles (e.g. "Workshop on Baking")
const formattedTitles = events.map(evt => {
    if (evt.category === "Food") {
        return `Workshop on ${evt.title.replace(" Workshop", "")}`;
    }
    return evt.title;
});
console.log("Formatted Event Display Titles:", formattedTitles);