import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddHuman() {
    const [form, setForm] = useState({
        passport: undefined,
        fullName: "",
        dateOfBirth: undefined
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
    return (
        <div className="container shadow py-2 bg-secondary bg-opacity-25 mt-5 text-dark">
            <div className="row my-2 me-auto">
                <label className="col">Номер паспорта</label>
                <input min={1} onChange={onChange} value={form.passport} className="col" type="number" name="passport" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">ФИО</label>
                <input onChange={onChange} value={form.fullName} className="col" type="text" name="fullName" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Дата рождения</label>
                <input onChange={onChange} value={form.dateOfBirth} className="col" type="date" name="dateOfBirth" />
            </div>
            <div className="row mt-4 mx-2">
                <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Добавить человека</button>
                <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
            </div>
        </div>
    );
}