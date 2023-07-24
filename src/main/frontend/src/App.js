import './App.css';
import RegisterPage from './Pages/register';
import Success from './Pages/success';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/registration' element={<RegisterPage/>} />
        <Route path='/success' element={<Success />} />
      </Routes>
    </Router>
  )
  
  
}

export default App;
