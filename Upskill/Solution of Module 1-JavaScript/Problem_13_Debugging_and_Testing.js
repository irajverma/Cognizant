// Mock debug exercise showing breakpoint triggers and payload verification
function debugFormSubmission(payload) {
    console.log("Step 1: Form submission process started.");
    
    // ADD BREAKPOINT HERE IN DEVTOOLS to inspect the payload keys/values
    const verifiedPayload = {
        name: payload.name.trim(),
        email: payload.email.toLowerCase(),
        timestamp: new Date()
    };
    
    console.log("Step 2: Payload verified and parsed:", verifiedPayload);
    
    // Inspect network request payloads inside Network tab
    console.log("Step 3: Sending verification request to endpoint...");
}

debugFormSubmission({ name: " Alice ", email: "ALICE@test.com" });