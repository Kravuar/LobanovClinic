import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

export default function AssignService() {
    const { passport } = useParams();
    const [patient, setPatient] = useState();
    const [form, setForm] = useState({
        medicePassport: undefined,
        serviceId: undefined,
        timestamp: undefined
    });

    const onChange = ({ target }) => {
        setForm({
            ...form,
            [target.name]: target.value
        })
    }

    const onSubmit = () => {
        console.log(form)
    }

    const navigate = useNavigate();
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + `/humans/${passport}`, null)
            .then(response => setPatient(response.data))
            .catch(error => console.log(error))
    }, []);

    if (patient)
        return (
            <div className="container shadow py-2 bg-secondary bg-opacity-25 mt-5 text-dark">
                <div className="container-fluid mt-3 shadow border border-primary">
                    <h1 className="text-center">Номер паспорта: {patient.passport}</h1>
                    <h1 className="text-center">ФИО: {patient.fullName}</h1>
                </div>
                <div className="row my-2 me-auto">
                    <label className="col">Паспорт врача</label>
                    <input min={0} onChange={onChange} value={form.medicePassport} className="col" type="number" name="medicPassport" />
                </div>
                <div className="row my-2 me-auto">
                    <label className="col">Номер услуги</label>
                    <input min={0} onChange={onChange} value={form.serviceId} className="col" type="number" name="serviceId" />
                </div>
                <div className="row my-2 me-auto">
                    <label className="col">Когда</label>
                    <input onChange={onChange} value={form.serviceId} className="col" type="datetime-local" name="timestamp" />
                </div>
                <div className="row mt-4 mx-2">
                    <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Назначить услугу</button>
                    <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
                </div>
            </div>
        );
    else
        return <h1 className="text-center text-danger">Загрузка...</h1>
}