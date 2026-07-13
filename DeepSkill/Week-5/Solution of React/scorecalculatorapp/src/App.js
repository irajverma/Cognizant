import React from 'react';
import CalculateScore from './Components/CalculateScore';
function App() {
  return (
    <div>
      <h1>Score Calculator</h1>
      <CalculateScore Name="Raj Verma" School="DAV Public School" Total={450} goal={500} />
    </div>
  );
}
export default App;
