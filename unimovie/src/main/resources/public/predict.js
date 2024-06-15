document.getElementById('predict-form').addEventListener('submit', async function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    let postData = '';
    

    formData.forEach((value, key) => {
        postData += encodeURIComponent(key) + '=' + encodeURIComponent(value) + '&';
    });

    postData = postData.slice(0, -1);
    const apiUrl = 'http://localhost:8080/features/movie_predict';

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: postData
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
