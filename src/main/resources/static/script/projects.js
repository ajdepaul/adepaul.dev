const allTagButtons = document.querySelectorAll('#tags .tagButtons button')
const technologyButtons = document.querySelectorAll('#technologyButtons button')
const languageButtons = document.querySelectorAll('#languageButtons button')

const searchInput = document.querySelector('#search input')
const projects = Array.from(document.querySelectorAll('#projects .project-container'))
    .map(elem => {
        return {
            "title": elem.querySelector('h4').innerText,
            "technologies": elem.dataset.technologies.split(','),
            "languages": elem.dataset.languages.split(','),
            "elem": elem
        }
    })

// reverse project columns based on last character of project title
projects.forEach(proj => {
    const charCode = proj.title.charCodeAt(proj.title.length - 1)

    const columns = proj.elem.querySelector('.project-content > .columns')
    if (charCode % 2 === 0) columns.classList.add('reversed')
})

// tag buttons events
allTagButtons.forEach(btn => {

    btn.addEventListener('click', () => {

        if (btn.classList.contains('require')) {
            btn.classList.remove('require')
            btn.classList.add('exclude')
        } else if (btn.classList.contains('exclude')) {
            btn.classList.remove('exclude')
        } else {
            btn.classList.add('require')
        }

        filterProjects()
    })
})

searchInput.addEventListener('input', filterProjects)

// show or hide projects according to tag buttons & search input
function filterProjects() {

    projects.forEach(proj => {

        proj.elem.classList.remove('disabled')

        // technologies filter
        technologyButtons.forEach(button => {
            const buttonText = button.innerText
            if (button.classList.contains('require') && !proj.technologies.includes(buttonText)) {
                proj.elem.classList.add('disabled')
            } else if (button.classList.contains('exclude') && proj.technologies.includes(buttonText)) {
                proj.elem.classList.add('disabled')
            }
        })

        // languages filter
        languageButtons.forEach(button => {
            const buttonText = button.innerText
            if (button.classList.contains('require') && !proj.languages.includes(buttonText)) {
                proj.elem.classList.add('disabled')
            } else if (button.classList.contains('exclude') && proj.languages.includes(buttonText)) {
                proj.elem.classList.add('disabled')
            }
        })

        // search filter
        if (!proj.title.toLowerCase().includes(searchInput.value.toLowerCase())) {
            proj.elem.classList.add('disabled')
        }
    })
}
