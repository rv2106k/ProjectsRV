// Mobile menu toggle with accessible attributes
function toggleMenu(){
    const nav = document.getElementById('navMenu');
    const btn = document.querySelector('.menu-toggle');
    const isOpen = nav.getAttribute('data-open') === 'true';
    nav.setAttribute('data-open', (!isOpen).toString());
    btn.setAttribute('aria-expanded', (!isOpen).toString());
    // rely on CSS to show/hide in responsive rules
    if(!isOpen){
        nav.style.display = 'flex';
        nav.style.flexDirection = 'column';
    } else {
        nav.style.display = 'none';
    }
}

// Admission form validation and submit handler
document.addEventListener('DOMContentLoaded', function(){
    // FAQ accordion
    document.querySelectorAll('.faq-question').forEach(function(btn){
        btn.addEventListener('click', function(){
            const expanded = btn.getAttribute('aria-expanded') === 'true';
            btn.setAttribute('aria-expanded', (!expanded).toString());
            const answer = btn.nextElementSibling;
            if(!expanded){
                answer.hidden = false;
            } else {
                answer.hidden = true;
            }
        });
    });

    const admissionForm = document.getElementById('admission-form');
    if(admissionForm){
        admissionForm.addEventListener('submit', function(e){
            e.preventDefault();
            const name = document.getElementById('name');
            const email = document.getElementById('email');
            const phone = document.getElementById('phone');
            const course = document.getElementById('course');
            const errorBox = document.getElementById('admission-error');
            const successBox = document.getElementById('admission-success');
            let errors = [];

            if(!name.value.trim()) errors.push('Please enter your full name.');
            if(!email.value.trim() || !/^\S+@\S+\.\S+$/.test(email.value)) errors.push('Please enter a valid email.');
            if(!/^[0-9]{10}$/.test(phone.value)) errors.push('Please enter a valid 10-digit phone number.');
            if(!course.value) errors.push('Please select a course.');

            if(errors.length){
                errorBox.style.display = 'block';
                errorBox.textContent = errors.join(' ');
                successBox.style.display = 'none';
                return;
            }

            // Simulate successful submission
            errorBox.style.display = 'none';
            admissionForm.reset();
            successBox.style.display = 'block';
            setTimeout(()=> successBox.style.display = 'none', 5000);
        });
    }

    // Contact form - simple handler
    const contactForm = document.getElementById('contact-form');
    if(contactForm){
        contactForm.addEventListener('submit', function(e){
            e.preventDefault();
            const success = document.getElementById('contact-success');
            contactForm.reset();
            success.style.display = 'block';
            setTimeout(()=> success.style.display = 'none', 4000);
        });
    }

    // Smooth scroll for internal links
    document.querySelectorAll('a[href^="#"]').forEach(function(a){
        a.addEventListener('click', function(e){
            const targetId = a.getAttribute('href').slice(1);
            const target = document.getElementById(targetId);
            if(target){
                e.preventDefault();
                target.scrollIntoView({behavior:'smooth', block:'start'});
                // close mobile menu if open
                const nav = document.getElementById('navMenu');
                if(nav.getAttribute('data-open') === 'true'){
                    toggleMenu();
                }
            }
        });
    });
});
