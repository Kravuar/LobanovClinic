import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory, { Type } from 'react-bootstrap-table2-editor';

export default function Patients() {
    const [patients, setPatients] = useState([]);
    
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + "/patients", null)
            .then(response => setPatients(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div className=" container-fluid mt-3 shadow py-3 border border-dark">
            <button className="btn btn-success mb-3">Добавить пациента</button>
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
                data={ patients.map(patient => ({
                    departmentId: patient.department.id,
                    departmentName: patient.department.name,
                    ward: patient.ward,
                    patientPassport: patient.patient.passport,
                    patientFullName: patient.patient.fullName,
                    patientDateOfBirth: patient.patient.dateOfBirth,
                    medicPassport: patient.medic.passport,
                    medicFullName: patient.medic.fullName,
                    delete: <button className="btn btn-danger">X</button>,
                })) } 
                columns={[
                    {
                        dataField: 'departmentId',
                        type: 'number',
                        text: 'Номер отделения',
                        validator: (newValue) => {
                            if (newValue > 0)
                                return true;
                            else return {
                                valid: false,
                                message: 'Номер отделения не может быть отрицательным.'
                            };
                        }
                    },
                    {
                        dataField: 'departmentName',
                        text: 'Название отделения',
                        editable: () => false
                    },
                    {
                        dataField: 'ward',
                        type: 'number',
                        text: 'Номер палаты',
                        editable: () => false
                    },
                    {
                        dataField: 'patientPassport',
                        type: 'number',
                        text: 'Паспорт пациента',
                        validator: (newValue) => {
                            if (newValue > 0)
                                return true;
                            else return {
                                valid: false,
                                message: 'Номер паспорта не может быть отрицательным.'
                            };
                        },
                        editable: () => true
                    },
                    {
                        dataField: 'patientFullName',
                        text: 'ФИО пациента',
                    },
                    {
                        dataField: 'patientDateOfBirth',
                        type: 'date',
                        text: 'Дата рождения пациента',
                        editor: {
                            type: Type.DATE,
                          }
                    },
                    {
                        dataField: 'medicPassport',
                        type: 'number',
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
                        dataField: 'medicFullName',
                        text: 'ФИО врача',
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