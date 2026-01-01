const BASE_URL = 'https://api.thecatapi.com/v1/images/search/';

const getCats = async () => {
    try {
        const data = await fetch(BASE_URL);
        const json = await data.json();
        return json[0].url; // Corrigido: acessa o primeiro item do array e pega a URL da imagem
    } catch (e) {
        console.log(e.message);
    }
};


const loadImg = async () => {
	const img = document.getElementsByTagName('img')[0];
	img.src = await getCats();
};

loadImg();

const btn = document.getElementById('change-cat');
btn.addEventListener('click', loadImg);
