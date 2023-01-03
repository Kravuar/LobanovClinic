import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddPosition() {
    const [form, setForm] = useState({
        name: "",
        salary: 0
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
                <label className="col">Название должности</label>
                <input onChange={onChange} value={form.name} className="col" type="text" name="name" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Оклад</label>
                <input min={0} onChange={onChange} value={form.salary} className="col" type="number" name="salary" />
            </div>
            <div className="row mt-4 mx-2">
                <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Добавить должность</button>
                <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
            </div>
        </div>
    );
}