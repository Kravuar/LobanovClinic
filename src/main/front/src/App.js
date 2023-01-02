import './App.css';
import Navbar from "./components/Navbar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import React from 'react';
import Patients from './pages/Patients';
import Medics from './pages/Medics';

class App extends React.Component {
    render() {
        return (
            <div className="App background">
                <BrowserRouter>
                    <Navbar />
                    <Routes>
                        <Route exact path="/">
                            <Route path="patients" element={<Patients />} />
                            <Route path="medics" element={<Medics />} />
                            {/* <Route path="management" element={<Main />} /> */}
                            {/* <Route path="services" element={<Main />} /> */}
                        </Route>
                    </Routes>
                </BrowserRouter>
            </div>
        );
    };
}

export default App;