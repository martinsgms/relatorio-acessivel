/* 
 * Skill para adicionar evento ao Díario Inteligente 
 *
 * @author Gabriel Martins dos Santos
 */

const Alexa = require('ask-sdk-core');

const AddAtividadeHandler = {
  canHandle(handlerInput) {
    return handlerInput.requestEnvelope.request.type === 'LaunchRequest'
      || (handlerInput.requestEnvelope.request.type === 'IntentRequest'
      && handlerInput.requestEnvelope.request.intent.name === 'AddAtividadeIntent');
  },
  async handle(handlerInput) {
    let outputSpeech = 'Não foi possível iniciar o registro de atividade.';

    await adicionarAtividadeAoDiario()
      .then((response) => {
        const data = JSON.parse(response);
        
        outputSpeech = `Pronto, adicionei a atividade ${data.descricao} no seu diário.`;
        
      })
      .catch((err) => {
        outputSpeech = `Erro ao se comunicar com o servidor: ${err.message}`
      });

    return handlerInput.responseBuilder
      .speak(outputSpeech)
      .getResponse();
  },
};

const adicionarAtividadeAoDiario = () => new Promise((resolve, reject) => {

    const postData = JSON.stringify({
        "idExame": 1,
        "descricao": "café da tarde",
        "timestampEvento": getBrDateTime()
    });

    const options = {
        hostname: 'ec2-3-238-199-54.compute-1.amazonaws.com',
        port: 31255,
        path: '/exame/evento',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJSQSBTZXJ2ZXIiLCJzdWIiOiIxIiwiaWF0IjoxNjM3MjYxODI3LCJleHAiOjE2MzczNDgyMjd9.l4-QIV-GA5ONi44UA7cQoLV7AJw6rDerPO7IgGzyVOk'
        }
    };

    const client = require('http');
    const request = client.request(options, (res) => {
        const body = [];
        res.on('data', (chunk) => body.push(chunk));
        res.on('end', () => resolve(body.join('')));
    });

    request.on('error', (err) => reject(err));
    request.write(postData);
});

const getBrDateTime = () => {
    let fullDate = new Date().toISOString().substring(0, 19)
    let hr = fullDate.substring(11, 13)
    let brHr = hr - 3
    return fullDate.replace(`T${hr}`, `T${brHr}`)
}

const ErrorHandler = {
  canHandle() {
    return true;
  },
  handle(handlerInput, error) {
    console.log(`Error handled: ${error.message}`);

    return handlerInput.responseBuilder
      .speak('Desculpe, não entendi. Por favor, tente novamente.')
      .reprompt('Desculpe, não entendi. Por favor, tente novamente')
      .getResponse();
  },
};

const SessionEndedRequestHandler = {
  canHandle(handlerInput) {
    return handlerInput.requestEnvelope.request.type === 'SessionEndedRequest';
  },
  handle(handlerInput) {
    console.log(`Sessão Encerrada pelo motivo: ${handlerInput.requestEnvelope.request.reason}`);

    return handlerInput.responseBuilder.getResponse();
  },
};

const CancelAndStopIntentHandler = {
  canHandle(handlerInput) {
    return handlerInput.requestEnvelope.request.type === 'IntentRequest'
      && (handlerInput.requestEnvelope.request.intent.name === 'AMAZON.CancelIntent'
        || handlerInput.requestEnvelope.request.intent.name === 'AMAZON.StopIntent');
  },
  handle(handlerInput) {
    const speechText = 'Tchau!';

    return handlerInput.responseBuilder
      .speak(speechText)
      .getResponse();
  },
};

const skillBuilder = Alexa.SkillBuilders.custom();

exports.handler = skillBuilder
  .addRequestHandlers(
    AddAtividadeHandler,
    CancelAndStopIntentHandler,
    SessionEndedRequestHandler,
  )
  .addErrorHandlers(ErrorHandler)
  .lambda();
