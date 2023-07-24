import { useNavigate } from "react-router-dom";
import { useState } from "react";
import "../register.css";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [errorDisplay, setErrorDisplay] = useState(null);

  function Header() {
    return (
      <header>
        <h2>AeroParker</h2>
      </header>
    );
  }

  function SubmitForm() {
    const [email, setEmail] = useState("");
    const [title, setTitle] = useState("Mr");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [addressLine1, setAddressLine1] = useState("");
    const [addressLine2, setAddressLine2] = useState("");
    const [city, setCity] = useState("");
    const [postcode, setPostcode] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    let handleSubmit = async (e) => {
      e.preventDefault();

      try {
        await fetch("http://localhost:8080/api/v1/aeroparker/user", {
          method: "POST",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify({
            emailAddress: email,
            title: title,
            firstName: firstName,
            lastName: lastName,
            addressLine1: addressLine1,
            addressLine2: addressLine2,
            city: city,
            postcode: postcode,
            phoneNumber: phoneNumber,
          }),
        })
          .then((response) => {
            if (response.status === 201) {
              navigate("/success");
            }
            if (!response.ok) {
              return response.json().then((err) => {
                throw new Error(err.message);
              });
            }
          })
          .catch((err) => {
            setErrorDisplay(err.message);
          });
      } catch (err) {
        console.log(err);
      }
    };

    return (
      <form onSubmit={(e) => handleSubmit(e)}>
        <div className="form-group">
          <label>Email</label>
          <input
            className="inputText"
            id="Email"
            type="email"
            placeholder="Email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Title</label>
          <select value={title} onChange={(e) => setTitle(e.target.value)}>
            <option>Mr</option>
            <option>Mrs</option>
            <option>Miss</option>
            <option>Ms</option>
            <option>Sir</option>
            <option>Dr</option>
          </select>
        </div>

        <div className="form-group">
          <label>First name</label>
          <input
            className="inputText"
            id="FirstName"
            type="text"
            placeholder="First name"
            maxLength={50}
            required
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Last name</label>
          <input
            className="inputText"
            id="LastName"
            type="text"
            placeholder="Last name"
            maxLength={50}
            required
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Address Line 1</label>
          <input
            className="inputText"
            id="AddressLine1"
            type="text"
            placeholder="Address line 1"
            required
            value={addressLine1}
            onChange={(e) => setAddressLine1(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Address Line 2</label>
          <input
            className="inputText"
            id="AddressLine2"
            type="text"
            placeholder="Address line 2 (optional)"
            value={addressLine2}
            onChange={(e) => setAddressLine2(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>City</label>
          <input
            className="inputText"
            id="City"
            type="text"
            placeholder="City (optional)"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Postcode</label>
          <input
            className="inputText"
            id="Postcode"
            type="text"
            placeholder="Postcode"
            maxLength={10}
            required
            value={postcode}
            onChange={(e) => setPostcode(e.target.value)}
          />
        </div>

        <div className="form-group">
          <label>Phone number</label>
          <p>Must start with +44</p>
          <input
            className="inputText"
            id="AddressLine1"
            type="text"
            maxLength={20}
            placeholder="Phone number (optional)"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
          />
        </div>

        <div id="onSuccess"></div>

        <input
          className="inputLogin"
          type="submit"
          value={"Register"}
          onClick={() => {
            document.getElementById("onSuccess").innerHTML = "Loading...";
            if (errorDisplay !== null) {
              document.getElementById("onSuccess").innerHTML = "";
            }
          }}
        />

        {errorDisplay && <div className="error"> {errorDisplay} </div>}
      </form>
    );
  }

  return (
    <div>
      <Header />
      <SubmitForm />
    </div>
  );
};

export default RegisterPage;
