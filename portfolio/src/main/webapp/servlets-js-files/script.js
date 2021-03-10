/** Fetches the hardcodedstring from the server and adds it to the page. */
async function showHardCodedString() {
  const responseFromServer = await fetch('/mystring');
  const messagesFromResponse = await responseFromServer.json();

  const stringContainer = document.getElementById('string-container');

  var totalMessages = messagesFromResponse.length;
  var randomNumber = getRandomInt(totalMessages);
  
    stringContainer.innerHTML = messagesFromResponse[randomNumber].message;

}
 /** Returns a random number between 0 and max-1 */
function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

