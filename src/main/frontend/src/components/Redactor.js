

import { useEffect, useState } from "react";
import { ButtonGroup, Col, Container, Row, Button, Form, Alert } from "react-bootstrap";




var result = [];
var exist = false;
var flagEx = false;

export default function Redactor() {

    const [newFunction, setNewFunction] = useState('');
    const [flag, setFlag] = useState(false);
    const [canCreateFunction, setCanCreateFunction] = useState(false);
    const [listFunctions, setListFunctions] = useState([])
    const [codeFunctionForUser, setCodeFunctionForUser] = useState('');
    const [getOneFunction, setGetOneFunction] = useState([]);
    const [indicatorForFunctionExist, setIndicatorForFunctionExist] = useState(false)
    const [currentFunction, setCurrentFunction] = useState([]);
    const [status, setStatus] = useState(200)
    var codeFunction = '';
   
    const headers = new Headers({
        'Authorization': 'Bearer ' + localStorage.getItem("bearer-token") 
    });
    const options = {
        method: 'GET',
        headers
    };


    useEffect(() => {
        fetch('http://localhost:8080/api/v1/function',options)
            .then(res => {if (res.status === 200) {setStatus(200); return res.json()} else {setStatus(res.status);}})
            .then(
                result => { if (status === 200) 
                    setListFunctions(result.result);
                    
                }
            )
    }, [listFunctions]);


    


    const handleClick = (e) => {
        e.preventDefault()
        checkExist()
        if (flag === false)
            setFlag(true);
        else setFlag(false);

    }

    const handleClickSave = (e) => {
        e.preventDefault()
        if (!indicatorForFunctionExist) {
            fetch('http://localhost:8080/api/v1/function',
                {
                    method: "POST",
                    headers: { "Content-Type": "application/json",
                             'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")  },
                    body: JSON.stringify({ "name": currentFunction[0], "code": currentFunction[1] })
                }).then((res) =>{if (res.status === 200) {setStatus(200); return res.json()} else {setStatus(res.status);}})
                .then((data) => {
                    if (status === 200 )
                    setCurrentFunction(data.result)
                });

        } else {
            currentFunction.code = getOneFunction;
            fetch('http://localhost:8080/api/v1/function',
                {
                    method: "PUT",
                    headers: { "Content-Type": "application/json",
                                'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")  },
                    body: JSON.stringify(currentFunction)
                }).then(res => {if (res.status === 200) {setStatus(200); return res.json()} else {setStatus(res.status);}})
                .then((data) => {
                    if (status === 200)
                    setCurrentFunction(data.result)
                });

        }
        flagEx = false;

    }

    const handleClickDelete = (e) => {
        e.preventDefault()
        console.log(currentFunction)
        fetch(`http://localhost:8080/api/v1/function/${currentFunction.id}`, {
            method: 'DELETE',
            headers: {'Authorization': 'Bearer ' + localStorage.getItem("bearer-token") }
        })
    }

    const createNewFunction = (e) => {
        e.preventDefault()
        const boole = listFunctions.map(s => { if (s.name === newFunction) { return false } else { return true } })
        if (!boole.includes(false)) {
            setCanCreateFunction(true)
            codeFunction = "function " + newFunction + "\n" + "#--------\n" + "def";
            setCodeFunctionForUser(codeFunction)
            setFlag(false)
            const mass = [newFunction, codeFunction]
            setCurrentFunction(mass)
            setIndicatorForFunctionExist(false);
        }
        else {
            setCanCreateFunction(false)
            setFlag(false)
            listFunctions.forEach(s => {
                if (s.name === newFunction) {
                    fetch(`http://localhost:8080/api/v1/function/${s.id}`,{
                   headers:{'Authorization': 'Bearer ' + localStorage.getItem("bearer-token") } })
                        .then(res => {if (res.status === 200) {setStatus(200); return res.json()} else {setStatus(res.status);}})
                        .then(
                            (data) => {
                                if (status === 200) {
                                setGetOneFunction(data.result);
                                setCurrentFunction(data.result);
                                }
                            }
                        );

                }
            })
            setIndicatorForFunctionExist(true);
        }

    }

    const checkExist = (e) => {
        if (currentFunction.code !== getOneFunction.code) {
            exist = true;
            flagEx = true;
        }
    }

    const handleClickRun = (e) => {
        e.preventDefault()
        fetch('http://localhost:8080/api/v1/function/execute',
            {
                method: "POST",
                headers: { "Content-Type": "application/json",
                           'Authorization': 'Bearer ' + localStorage.getItem("bearer-token")  },
                body: JSON.stringify({ "function": currentFunction.id })
            }).then(res => {if (res.status === 200) {setStatus(200); return res.json()} else {setStatus(res.status);}})
            .then((data) => {

                if (data.result !== null && status === 200) {
                    console.log(result)
                    result.push(data.result)
                } else if(status === 200){
                    result.push(data.error)
                }
            });

    }

    const handleClickNo = (e) => {
        e.preventDefault()
        exist = false;
        flagEx = false;

    }

    const handleClickYes = (e) => {
        handleClickSave(e);
        exist = false;
        flagEx = false;

    }

    return (
        
        <Container>
      {status === 200 &&
            <Row>
                <Col sm={2} style={{ border: '2px solid black', paddingBottom: '700px', marginRight: '-1.2px', maxHeight: "733.5px", fontSize: "20px",minHeight:"733.5" }}><div style={{ textDecoration: "underline", fontWeight: "bold" }}>functionList:</div>
                    {listFunctions.map((s, i) => <div>{i + 1}.{s.name}</div>)}
                </Col>
                <Col sm={8}>
                    <Row style={{ border: '2px solid black', paddingBottom: '31px', marginBottom: '-1px' }}>
                        <ButtonGroup className="mb-2" size="sm" style={{ paddingTop: '20px' }}>
                            <Button onClick={handleClick} disabled={exist}>New</Button>
                            <Button onClick={handleClickDelete} disabled={(exist || !canCreateFunction ) && !indicatorForFunctionExist}>Delete</Button>
                            <Button onClick={handleClickSave} disabled={(exist || !canCreateFunction ) && !indicatorForFunctionExist }>Save</Button>
                            <Button onClick={handleClickRun} disabled={(exist || !canCreateFunction ) && !indicatorForFunctionExist }>Run</Button>
                        </ButtonGroup>
                    </Row>
                    <Row style={{ border: '2px solid black'}}>
                        {flag && !flagEx &&
                            <Form>
                                <Form.Group className="mb-3" controlId="exampleForm.ControlInput1" style={{ minHeight:"518px", maxHeight: '521.5px' }}>
                                    <Form.Label style={{ fontSize: "20px" }}>Function name</Form.Label>
                                    <Form.Control type="text" placeholder="Enter function name" onChange={(e) => setNewFunction(e.target.value)} value={newFunction} />
                                    <Button onClick={createNewFunction} style={{ marginTop: '5px' }}>Create or open</Button>
                                </Form.Group>
                            </Form>
                        }
                        {!flag && !canCreateFunction && !indicatorForFunctionExist &&
                            <Form.Control as="textarea" placeholder="Enter your code" style={{ minHeight:"524px", fontFamily: "Courier Sans", fontSize: "20px", maxHeight: '537.5px' }} />
                        }

                        {canCreateFunction && !flag &&

                            <Form.Control as="textarea" onChange={(e) => setCodeFunctionForUser(e.target.value)} value={codeFunctionForUser} style={{ maxHeight: '537.5px', paddingBottom: '463px', fontFamily: "Courier Sans", fontSize: "20px" }} />

                        }
                        {
                            !canCreateFunction && !flag && indicatorForFunctionExist &&

                            <Form.Control as="textarea" onChange={(e) => setGetOneFunction(e.target.value)} value={getOneFunction.code} style={{ maxHeight: '537.5px', paddingBottom: '463px', fontFamily: "Courier Sans", fontSize: "20px" }} />
                        }
                        {
                            exist &&

                            <Alert variant="info" style={{ marginTop: "100px", marginBottom: "366px", marginLeft: "150px", width: "600px", textAlign: "center" }}>
                                Do you want save this change?
                                <Button variant="warning" onClick={handleClickYes} style={{ marginRight: "10px", marginLeft: "5px" }}>Yes</Button>
                                <Button variant="warning" onClick={handleClickNo}>No</Button>
                            </Alert>
                        }


                    </Row>
                    <Row style={{ border: '2px solid black' }}>
                        <div id="div1" style={{ height: '110px', position: 'relative' }}>
                            <div id="div2" style={{ maxHeight: '100%', overflow: 'auto' }}>
                                <div id="div3" style={{ height: 'auto', fontSize: "20px" }}> function logback:

                                    {result.map(s => <li># {s}</li>)}

                                </div>
                            </div>
                        </div>
                    </Row>
                </Col>
            </Row>
} { status !== 200 &&
<div>Error status {status}</div>


}

        </Container>
    

    )

}