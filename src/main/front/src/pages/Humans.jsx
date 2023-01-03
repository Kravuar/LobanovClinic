import axios from "axios";
import { useEffect, useState } from "react";
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory, { Type } from 'react-bootstrap-table2-editor';
import { Link } from "react-router-dom";

export default function Humans() {
    const [humans, setHumans] = useState([]);
    
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + "/humans", null)
            .then(response => setHumans(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div className=" container-fluid mt-3 shadow py-3 border border-dark">
            <Link to="addHuman" className="btn btn-success mb-3">Добавить человека</Link>
            <BootstrapTable 
                keyField='passport' 
                cellEdit={ cellEditFactory({ 
                    mode: 'click',
                    afterSaveCell: (_0,_1, row) => {
                        // do certain action depending on column
                    }
                }) }
                data={ humans.map(human => ({
                    passport: human.passport,
                    fullName: human.fullName,
                    dateOfBirth: human.dateOfBirth,
                    delete: <button className="btn btn-danger">X</button>,
                })) } 
                columns={[
                    {
                        dataField: 'passport',
                        type: 'number',
                        text: 'Паспорт человека',
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
                        dataField: 'fullName',
                        text: 'ФИО человека',
                    },
                    {
                        dataField: 'dateOfBirth',
                        text: 'Дата рождения человека',
                        editor: {
                            type: Type.DATE,
                        }
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