import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory, { Type } from 'react-bootstrap-table2-editor';

export default function Medics() {
    const [medics, setMedics] = useState([]);
    
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + "/medics", null)
            .then(response => setMedics(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div className=" container-fluid mt-3 shadow py-3 border border-dark">
            <button className="btn btn-success mb-3">Добавить врача</button>
            <BootstrapTable 
                keyField='patientPassport' 
                cellEdit={ cellEditFactory({ 
                    mode: 'click',
                    afterSaveCell: (_0,_1, row) => {
                        axios.post(process.env.REACT_APP_API_URL + "/patients/update", {
                            patientPassport: row.patientPassport,
                            medicPassport: row.medicPassport,
                            departmentId: row.departmentId,
                            ward: row.ward
                        });
                    }
                }) }
                data={ medics.map(medic => ({
                    positions: medic.positions,
                    passport: medic.patient.passport,
                    fullName: medic.patient.fullName,
                    dateOfBirth: medic.patient.dateOfBirth,
                    positions: <ul>
                        {
                            medic.positions.map(position => 
                                <li key={'' + position.department.id + position.position.id}>
                                    {position.department.name + position.position.name}
                                </li>
                            )
                        }
                    </ul>,
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
                        text: 'Дата рождения пациента',
                        editor: {
                            type: Type.DATE,
                        }
                    },
                    {
                        dataField: 'positions',
                        text: 'Должности',
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