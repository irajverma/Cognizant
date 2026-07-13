import React from 'react';
function App() {
    const title = "Office Space Rental Portal";
    const officeImage = "https://images.unsplash.com/photo-1497366216548-37526070297c?w=300";
    
    const mainOffice = {
        Name: "Elite Workspace",
        Rent: 75000,
        Address: "123 Tech Park, Bangalore"
    };

    const officeSpaces = [
        { Name: "Co-working Station", Rent: 45000, Address: "Sector 62, Noida" },
        { Name: "Executive Suite", Rent: 90000, Address: "Bandra Kurla Complex, Mumbai" },
        { Name: "Startup Hub", Rent: 55000, Address: "Salt Lake Sector V, Kolkata" },
        { Name: "Enterprise Office", Rent: 120000, Address: "Hitec City, Hyderabad" }
    ];

    return (
        <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
            <h1>{title}</h1>
            <div style={{ border: '1px solid #ccc', padding: '15px', marginBottom: '20px', width: '350px' }}>
                <h3>Primary Office</h3>
                <img src={officeImage} alt="Office" style={{ width: '100%', borderRadius: '4px' }} />
                <h4>{mainOffice.Name}</h4>
                <p>Address: {mainOffice.Address}</p>
                <p>Rent: <span style={{ color: mainOffice.Rent < 60000 ? 'red' : 'green', fontWeight: 'bold' }}>${mainOffice.Rent}</span></p>
            </div>

            <h2>All Office Spaces</h2>
            <div style={{ display: 'flex', flexWrap: 'wrap' }}>
                {officeSpaces.map((office, idx) => (
                    <div key={idx} style={{ border: '1px solid #ddd', padding: '10px', margin: '10px', width: '220px', borderRadius: '4px' }}>
                        <h4>{office.Name}</h4>
                        <p>{office.Address}</p>
                        <p>Rent: <span style={{ color: office.Rent < 60000 ? 'red' : 'green', fontWeight: 'bold' }}>${office.Rent}</span></p>
                    </div>
                ))}
            </div>
        </div>
    );
}
export default App;
