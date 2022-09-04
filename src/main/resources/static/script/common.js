const navbarMenuBtn = document.querySelector('#navbar-menu button')
const navbarMenuList = document.querySelector('#navbar-menu ul')
const codeBlocks = document.querySelectorAll('pre code')

// navbar menu button action
navbarMenuBtn.addEventListener('click', () => navbarMenuList.classList.toggle('disabled'))

// code block formatting
codeBlocks.forEach(code => {
    var lines = code.textContent.split('\n')

    // remove leading empty lines
    while (lines.length != 0 && lines[0].trim().length === 0) {
        lines.shift()
    }

    // remove trailing empty lines
    while (lines.length != 0 && lines[lines.length - 1].trim().length === 0) {
        lines.pop()
    }
    
    if (lines.length == 0) return

    // count first line's leading spaces
    var prefixSize = 0
    while (lines[0].charAt(prefixSize) === ' ') {
        prefixSize++
    }

    code.textContent = lines.map(line => line.substring(prefixSize)).join('\n')
})
