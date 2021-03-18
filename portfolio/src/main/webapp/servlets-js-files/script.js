/** Fetches the hardcodedstring from the server and adds it to the page. */
async function showHardCodedString() {
  const responseFromServer = await fetch('/mystring');
  const messagesFromResponse = await responseFromServer.json();

  const stringContainer = document.getElementById('fact-container');

  var totalMessages = messagesFromResponse.length;
  var randomNumber = getRandomInt(totalMessages);
  
    stringContainer.innerHTML = messagesFromResponse[randomNumber].message;
    setTimeout(showHardCodedString, 2000);

}
 /** Returns a random number between 0 and max-1 */
function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

/**fetches messages from server and adds them to DOM */
function loadMessages() {
  fetch('/load-messages').then(response => response.json()).then((messages) => {
    const messageElement = document.getElementById('messages-list');
    messages.forEach((message) => {
        messageElement.appendChild(createMessageElement(message));
    })
  });
}

/** Creates an element that represents a message */
function createMessageElement(message) {
    const contactElement = document.createElement('li');
    contactElement.className = 'contact';

    const firstnameElement = document.createElement('span');
    firstnameElement.innerText = message.firstname;

    const lastnameElement = document.createElement('span');
    lastnameElement.innerText = lastname.email;

    const emailElement = document.createElement('span');
    emailElement.innerText = message.email;
    
    const messageElement = document.createElement('span');
    messageElement.innerText = message.message; 

    const deleteButtonElement = document.createElement('button');
    deleteButtonElement.innerText = 'Delete';
    deleteButtonElement.addEventListener('click', () => {
        deleteMessage(message);
        // Remove the contact from the DOM.
        contactElement.remove();
    });

    contactElement.appendChild(firstnameElement);
    contactElement.appendChild(lastnameElement);
    contactElement.appendChild(emailElement);
    contactElement.appendChild(messageElement);
    contactElement.appendChild(deleteButtonElement);
  
    return contactElement;
}

/** Tells the server to delete the message. */
function deleteMessage(message) {
  const params = new URLSearchParams();
  params.append('id', message.id);
  fetch('/delete-message', {method: 'POST', body: params});
}

