import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

export default function AddMedicPosition() {
    const { passport } = useParams();
    const [medic, setMedic] = useState();
    const [form, setForm] = useState({
        departmentId: undefined,
        positionId: undefined
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
        axios.get(process.env.REACT_APP_API_URL + `/medics/${passport}`, null)
            .then(response => setMedic(response.data))
            .catch(error => console.log(error))
    }, []);

    if (medic)
        return (
            <div className="container shadow py-2 bg-secondary bg-opacity-25 mt-5 text-dark">
                <div className="container-fluid mt-3 shadow border border-primary">
                    <h1 className="text-center">Номер паспорта: {medic.medic.passport}</h1>
                    <h1 className="text-center">ФИО: {medic.medic.fullName}</h1>
                </div>
                <div className="row my-2 me-auto">
                    <label className="col">Номер должности</label>
                    <input min={0} onChange={onChange} value={form.positionId} className="col" type="number" name="positionId" />
                </div>
                <div className="row my-2 me-auto">
                    <label className="col">Номер отделения</label>
                    <input min={0} onChange={onChange} value={form.departmentId} className="col" type="number" name="departmentId" />
                </div>
                <div className="row mt-4 mx-2">
                    <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Добавить должность</button>
                    <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
                </div>
            </div>
        );
    else
        return <h1 className="text-center text-danger">Загрузка...</h1>
}