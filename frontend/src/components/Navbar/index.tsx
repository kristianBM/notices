import { ReactComponent as GithubIcon } from 'assets/img/github.svg';
import './styles.css'

function Navbar() {
    return (
        <header>
            <nav className='container' id='notices-navbar'>
                <div className='notices-nav-content'>
                    <a href="/"><h1>ssnotices</h1></a>
                    <a href="https://github.com/kristianBM">
                        <div className="notices-contact-container">
                            <GithubIcon />
                            <p className="notices-contact-link">/kristianbm</p>
                        </div>
                    </a>
                </div>
            </nav>
        </header>
    );
}

export default Navbar;