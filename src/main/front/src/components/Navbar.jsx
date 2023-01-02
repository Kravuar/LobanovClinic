import React from "react";
import { Link } from "react-router-dom";

class Navbar extends React.Component {
    render() {
        return (
            <nav className="navbar shadow">
                <div className="container-fluid mx-2">
                    <div className="nav">
                        <Link to="/humans" className="btn btn-outline-secondary text-dark ms-2">Люди</Link>
                        <Link to="/patients" className="btn btn-outline-secondary text-dark ms-2">Пациенты</Link>
                        <Link to="/medics" className="btn btn-outline-secondary text-dark ms-2">Врачи</Link>
                        <Link to="/services" className="btn btn-outline-secondary text-dark ms-2">Услуги</Link>
                        <Link to="/myPatients" className="btn btn-outline-secondary text-dark ms-2">Мои пациенты</Link>
                        <Link to="/supervisor" className="btn btn-outline-secondary text-dark ms-2">Управление Отделениями</Link>
                        <Link to="/management" className="btn btn-outline-secondary text-dark ms-2">Управление Клиникой</Link>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;