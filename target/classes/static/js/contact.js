// set the modal menu element
const viewContactModal = document.getElementById('view_contact_modal');

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};


function openContactModal()
{
    const contactModal = new Modal(viewContactModal, options, instanceOptions);
    contactModal.show();
}

function closeContactModal()
{
    const contactModal = new Modal(viewContactModal, options, instanceOptions);
    contactModal.hide();
}

async function loadContactdata(id)
{
    console.log(id);
    try{
        const data=await( await fetch(`http://localhost:8081/api/contacts/${id}`)).json();
        console.log(data.linkedinLink);
        console.log(data.name);
        document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#contact_address").innerHTML = data.address;
        document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#contact_about").innerHTML = data.description;
        document.querySelector("#contact_websiteLink").innerHTML = data.websiteLink;
        document.querySelector("#contact_linkedinLink").innerHTML = data.linkedinLink;

        openContactModal();

    } catch(error)
    {
        console.log(error);
    }

}