import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddPatient() {
    const [form, setForm] = useState({
        patientPassport: undefined,
        medicPassport: undefined,
        ward: undefined,
        departmentId: undefined,
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
                <label className="col">Номер паспорта пациента</label>
                <input min={1} onChange={onChange} value={form.patientPassport} className="col" type="number" name="patientPassport" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Номер паспорта врача</label>
                <input min={1} onChange={onChange} value={form.medicPassport} className="col" type="number" name="medicPassport" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Номер отделения</label>
                <input min={1} onChange={onChange} value={form.departmentId} className="col" type="number" name="departmentId" />
            </div>
            <div className="row my-2 me-auto">
                <label className="col">Номер палаты</label>
                <input min={1} onChange={onChange} value={form.ward} className="col" type="number" name="ward" />
            </div>
            <div className="row mt-4 mx-2">
                <button className="col btn btn-outline-success my-2 me-2" onClick={onSubmit}>Добавить пациента</button>
                <button className="col btn btn-outline-danger my-2 ms-2" onClick={() => navigate(-1)}>Отменить</button>
            </div>
        </div>
    );
}