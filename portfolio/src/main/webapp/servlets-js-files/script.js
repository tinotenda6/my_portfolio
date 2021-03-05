/** Fetches the hardcodedstring from the server and adds it to the page. */
async function showHardCodedString() {
  const responseFromServer = await fetch('/mystring');
  const textFromResponse = await responseFromServer.text();

  const stringContainer = document.getElementById('string-container');
  stringContainer.innerText = textFromResponse;
}