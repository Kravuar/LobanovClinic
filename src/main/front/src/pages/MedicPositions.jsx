import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory from 'react-bootstrap-table2-editor';
import { Link, useParams } from "react-router-dom";

export default function MedicPositions() {
    const { passport } = useParams();
    const [medic, setMedic] = useState();

    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + `/medics/${passport}`, null)
            .then(response => setMedic(response.data))
            .catch(error => console.log(error))
    }, []);

    if (medic)
        return (
            <div>
                <div className="container-fluid mt-3 shadow border border-primary">
                    <h1 className="text-center">Номер паспорта: {medic.medic.passport}</h1>
                    <h1 className="text-center">ФИО: {medic.medic.fullName}</h1>
                </div>
                <div className=" container-fluid mt-3 shadow py-3 border border-dark">
                    <Link to={`/medics/addMedicPosition/${medic.medic.passport}`} className="btn btn-success mb-3">Добавить должность</Link>
                    <BootstrapTable
                        keyField='idx'
                        cellEdit={cellEditFactory({
                            mode: 'click',
                            afterSaveCell: (_0, _1, row) => {
                                //
                            }
                        })}
                        data={medic.positions.map((position, idx) => ({
                            idx: idx,
                            departmentId: position.department.id,
                            departmentName: position.department.name,
                            positionId: position.position.id,
                            positionName: position.position.name,
                            positionSalary: position.position.salary,
                            delete: <button className="btn btn-danger">X</button>,
                        }))}
                        columns={[
                            {
                                dataField: 'idx',
                                text: '',
                                hidden: true
                            },
                            {
                                dataField: 'departmentId',
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
                                dataField: 'positionId',
                                text: 'Номер должности',
                                validator: (newValue) => {
                                    if (newValue > 0)
                                        return true;
                                    else return {
                                        valid: false,
                                        message: 'Номер должности не может быть отрицательным.'
                                    };
                                }
                            },
                            {
                                dataField: 'positionName',
                                text: 'Название должности',
                                editable: () => false
                            },
                            {
                                dataField: 'delete',
                                text: '',
                                editable: () => false
                            }
                        ]} />
                </div>
            </div>
        );
    else
        return <h1 className="text-center text-danger">Загрузка...</h1>
}