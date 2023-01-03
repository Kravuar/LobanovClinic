import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory, { Type } from 'react-bootstrap-table2-editor';
import { Link } from "react-router-dom";

export default function Medics() {
    const [medics, setMedics] = useState([]);
    
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + "/medics", null)
            .then(response => setMedics(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div className=" container-fluid mt-3 shadow py-3 border border-dark">
            <Link to="addMedic" className="btn btn-success mb-3">Добавить врача</Link>
            <BootstrapTable 
                keyField='passport' 
                cellEdit={ cellEditFactory({ 
                    mode: 'click',
                    afterSaveCell: (_0,_1, row) => {
                        //
                    }
                }) }
                data={ medics.map(medic => ({
                    positions: medic.positions,
                    passport: medic.medic.passport,
                    fullName: medic.medic.fullName,
                    dateOfBirth: medic.medic.dateOfBirth,
                    phoneNumber: medic.phoneNumber,
                    positions: <ul>
                        {
                            medic.positions.map(position => 
                                <li key={'' + position.department.id + position.position.id}>
                                    {position.department.name + '  -   ' + position.position.name}
                                </li>
                            )
                        }
                    </ul>,
                    edit: <Link to={`managePositions/${medic.medic.passport}`} className="btn btn-warning">Редактировать должности</Link>,
                    delete: <button className="btn btn-danger">X</button>,
                })) } 
                columns={[
                    {
                        dataField: 'passport',
                        text: 'Паспорт врача',
                        validator: (newValue) => {
                            if (newValue > 0)
                                return true;
                            else return {
                                valid: false,
                                message: 'Номер паспорта не может быть отрицательным.'
                            };
                        }
                    },
                    {
                        dataField: 'fullName',
                        text: 'ФИО врача',
                    },
                    {
                        dataField: 'dateOfBirth',
                        text: 'Дата рождения врача',
                        editor: {
                            type: Type.DATE,
                        }
                    },
                    {
                        dataField: 'phoneNumber',
                        text: 'Номер телефона'
                    },
                    {
                        dataField: 'positions',
                        text: 'Должности',
                        editable: () => false
                    },
                    {
                        dataField: 'edit',
                        text: '',
                        editable: () => false
                    },
                    {
                        dataField: 'delete',
                        text: '',
                        editable: () => false
                    }
                ]} />
        </div>
    );
}