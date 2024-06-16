document.getElementById('predict-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const form = this;
    const formData = new FormData(form);
    let factor = '';

    formData.forEach((value, key) => {
        if (factor) {
            factor += ',';
        }
        factor += value;
    });

    const apiUrl = 'http://localhost:8080/features/movie_predict';
    const data = new FormData();
    data.append('factor', factor);

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            body: data
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const responseData = await response.json();
        console.log(responseData);

        if (responseData.result === 'HIT') {
            window.location.href = '/HIT.html';
        } else if (responseData.result === 'AVG') {
            window.location.href = '/AVG.html';
        } else {
            window.location.href = '/FLOP.html';
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
});
