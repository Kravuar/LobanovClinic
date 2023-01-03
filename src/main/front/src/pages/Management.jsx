import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory, { Type } from 'react-bootstrap-table2-editor';
import { Link } from "react-router-dom";

export default function Management() {
    const [positions, setPositions] = useState([]);
    const [services, setServices] = useState([]);

    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + "/positions", null)
            .then(response => setPositions(response.data))
            .catch(error => console.log(error));
        axios.get(process.env.REACT_APP_API_URL + "/services", null)
            .then(response => setServices(response.data))
            .catch(error => console.log(error));
    }, []);

    return (
        <div className="row">
            <div className="col">
                <div className="mt-3 shadow py-3 border border-dark">
                    <Link to="addPosition" className="btn btn-success mb-3">Добавить должность</Link>
                    <BootstrapTable
                        keyField='positionId'
                        cellEdit={cellEditFactory({
                            mode: 'click',
                            afterSaveCell: (_0, _1, row) => {
                                // do certain action depending on column.
                            }
                        })}
                        data={positions.map(position => ({
                            positionId: position.id,
                            positionName: position.name,
                            positionSalary: position.salary,
                            delete: <button className="btn btn-danger">X</button>,
                        }))}
                        columns={[
                            {
                                dataField: 'positionId',
                                type: 'number',
                                text: 'Номер должности',
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
                                dataField: 'positionName',
                                text: 'Название должности',
                            },
                            {
                                dataField: 'positionSalary',
                                type: 'number',
                                text: 'Оклад',
                            }
                        ]} />
                </div>
            </div>
            <div className="col">
                <div className="mt-3 shadow py-3 border border-dark">
                    <Link to="addService" className="btn btn-success mb-3">Добавить услугу</Link>
                    <BootstrapTable
                        keyField='serviceId'
                        cellEdit={cellEditFactory({
                            mode: 'click',
                            afterSaveCell: (_0, _1, row) => {
                                // do certain action depending on column.
                            }
                        })}
                        data={services.map(service => ({
                            serviceId: service.id,
                            serviceName: service.name,
                            serviceDescription: service.description,
                            servicePrice: service.price,
                            delete: <button className="btn btn-danger">X</button>,
                        }))}
                        columns={[
                            {
                                dataField: 'serviceId',
                                type: 'number',
                                text: 'Номер услуги',
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
                                dataField: 'serviceName',
                                text: 'Название услуги',
                            },
                            {
                                dataField: 'serviceDescription',
                                text: 'Описание услуги',
                            },
                            {
                                dataField: 'servicePrice',
                                type: 'number',
                                text: 'Цена',
                            }
                        ]} />
                </div>
            </div>
        </div>
    );
}