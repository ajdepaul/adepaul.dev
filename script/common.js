const effectBtn = document.getElementById('effect-btn')
const pixelEffect = document.getElementById('pixel-effect')
const vignette = document.getElementById('vignette')

document.cookie.split(';').forEach(cookie => {
    const pair = cookie.trim().split('=')
    if (pair[0] === 'effectDimLevel' && pair[1] === 'false') {
        effectToggle()
    }
})

function effectToggle() {
    pixelEffect.classList.toggle('disabled')
    if (vignette) vignette.classList.toggle('disabled')
}

effectBtn.addEventListener('click', () => {
    effectToggle()

    if (pixelEffect.classList.contains('disabled')) {
        document.cookie = `effectDimLevel=false; SameSite=Strict; path=/`
    } else {
        document.cookie = `effectDimLevel=true; SameSite=Strict; path=/`
    }
})