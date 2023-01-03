import React from "react";
import { Link } from "react-router-dom";

class Navbar extends React.Component {
    render() {
        return (
            <nav className="navbar navbar-light shadow bg-light">
                <div className="container-fluid">
                    <div>
                        <Link to="/humans" className="btn btn-outline-secondary text-dark ms-2">Люди</Link>
                        <Link to="/patients" className="btn btn-outline-secondary text-dark ms-2">Пациенты</Link>
                        <Link to="/medics" className="btn btn-outline-secondary text-dark ms-2">Врачи</Link>
                        <Link to="/management" className="btn btn-outline-secondary text-dark ms-2">Управление</Link>
                        <Link to="/myPatients" className="btn btn-outline-secondary text-dark ms-2">Мои пациенты</Link>
                    </div>
                    <Link className="btn btn-success">Людвиг Гумбольдт</Link>
                </div>
            </nav>
        );
    }
}

export default Navbar;