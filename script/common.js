const navbarMenuBtn = document.querySelector('#navbar-menu button')
const navbarMenuList = document.querySelector('#navbar-menu ul')

navbarMenuBtn.addEventListener('click', () => navbarMenuList.classList.toggle('disabled'))
