import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddService() {
    const [form, setForm] = useState({
        name: "",
        description: "",
        price: 0
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
                <label className="col">Название услуги</label>
                <input onChange={onChange} value={form.name} className="col" type="text" name="name" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Описание</label>
                <input onChange={onChange} value={form.description} className="col" type="text" name="description" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Цена</label>
                <input min={0} onChange={onChange} value={form.price} className="col" type="number" name="price" />
            </div>
            <div className="row mt-4 mx-2">
                <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Добавить услугу</button>
                <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
            </div>
        </div>
    );
}