import './App.css';
import Navbar from "./components/Navbar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import React from 'react';
import Patients from './pages/Patients';
import Medics from './pages/Medics';
import Humans from './pages/Humans';
import AddHuman from './pages/AddHuman';
import AddPatient from './pages/AddPatient';
import AddMedic from './pages/AddMedic';
import AddPosition from './pages/AddPosition';
import AddService from './pages/AddService';
import MedicPositions from './pages/MedicPositions';
import AssignService from './pages/AssignService';
import AddMedicPosition from './pages/AddMedicPosition';
import Management from './pages/Management';

class App extends React.Component {
    render() {
        return (
            <div className="App background">
                <BrowserRouter>
                    <Navbar />
                    <Routes>
                        <Route exact path="/">
                            <Route exact path="humans">
                                <Route path="" element={<Humans />} />
                                <Route path="addHuman" element={<AddHuman />} />
                            </Route>
                            <Route exact path="patients">
                                <Route path="" element={<Patients />} />
                                <Route path="assignService/:passport" element={<AssignService />} />
                                <Route path="addPatient" element={<AddPatient />} />
                            </Route>
                            <Route exact path="medics">
                                <Route path="" element={<Medics />} />
                                <Route path="addMedic" element={<AddMedic />} />
                                <Route path="addMedicPosition/:passport" element={<AddMedicPosition />} />
                                <Route path="managePositions/:passport" element={<MedicPositions />} />
                            </Route>
                            <Route exact path="management">
                                <Route path="" element={<Management />} />
                                <Route path="addPosition" element={<AddPosition />} />
                                <Route path="addService" element={<AddService />} />
                            </Route>
                        </Route>
                    </Routes>
                </BrowserRouter>
            </div>
        );
    };
}

export default App;