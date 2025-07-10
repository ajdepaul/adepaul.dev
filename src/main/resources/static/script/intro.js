const splash = document.getElementById('splash')

const body = document.body

const bootSequence = document.getElementById('boot-sequence')
const bootSequenceOutputGroups = document.querySelectorAll('#boot-sequence .output-group')
const timeStamps = document.querySelectorAll('.time-stamp')

const lineHeightProperty = getComputedStyle(document.documentElement).getPropertyValue('--line-height').trim()
const lineHeight = parseFloat(lineHeightProperty)
let i
for (i = 0; i < lineHeightProperty.length; i++) {
    if (!lineHeightProperty.charAt(i).toString().match(/[0-9.]/)) break
}
const lineHeightUnit = lineHeightProperty.substring(i, lineHeightProperty.length)

document.addEventListener('keyup', e => {
    if (e.code === 'Escape') window.location.href = "/projects/adepaul.dev#design"
})

/* --------------------------- animation sequence --------------------------- */

let time = 0

function offScreen() {
    body.classList.remove('dark-black')
    splash.classList.add('disabled')
}

function blackScreen() {
    body.classList.add('dark-black')
    splash.classList.add('disabled')
}

function splashDim() {
    splash.classList.remove('disabled')
    splash.classList.add('dim')
}

function splashOn() {
    splash.classList.remove('disabled')
    splash.classList.remove('dim')
}

time += 1000

setTimeout(offScreen, time)
time += 100

setTimeout(blackScreen, time)
time += 200

setTimeout(offScreen, time)
time += 100

setTimeout(blackScreen, time)
time += 100

setTimeout(splashDim, time)
time += 1000

setTimeout(splashOn, time)
time += 3100

function addPrefix(char, str, len) {
    let result = str
    for (let i = 0; i < len - str.length; i++) {
        result = `${char}${result}`
    }
    return result
}

let lines = 0
function transformNLines(n) {
    lines += n
    splash.style.transform = `translateY(-${lines * lineHeight}${lineHeightUnit})`
    bootSequence.style.transform = `translateY(-${lines * lineHeight}${lineHeightUnit})`
}

let printedGroups = 0
function bootSequenceNextGroup() {
    bootSequenceOutputGroups[printedGroups].classList.remove('disabled')

    const lines = bootSequenceOutputGroups[printedGroups].querySelectorAll('p')

    // update time stamps
    const date = new Date()
    const year = date.getFullYear()
    const month = addPrefix("0", (date.getMonth() + 1).toString(), 2)
    const day = addPrefix("0", date.getDate().toString(), 2)
    const hours = addPrefix("0", date.getHours().toString(), 2)
    const minutes = addPrefix("0", date.getMinutes().toString(), 2)
    const seconds = addPrefix("0", date.getSeconds().toString(), 2)
    lines.forEach(line => {
        line.querySelectorAll('span.time-stamp').forEach(timeStamp => {
            timeStamp.textContent = `[ ${year}-${month}-${day} ${hours}:${minutes}:${seconds} ] `
        })
    })
    
    transformNLines(lines.length)
    printedGroups++
}

setTimeout(() => {
    bootSequence.classList.remove('disabled')
    transformNLines(2)  // padding
    bootSequenceNextGroup()
}, time)
time += 500

setTimeout(bootSequenceNextGroup, time)
time += 1750

setTimeout(bootSequenceNextGroup, time)
time += 1000

setTimeout(bootSequenceNextGroup, time)
time += 500

setTimeout(bootSequenceNextGroup, time)
time += 1500

setTimeout(() => {
    splash.classList.add('disabled')
    bootSequence.classList.add('disabled')
    blackScreen.classList.remove('disabled')
}, time)
time += 500

setTimeout(() => { window.location.href = "/projects/adepaul.dev#design" }, time)
