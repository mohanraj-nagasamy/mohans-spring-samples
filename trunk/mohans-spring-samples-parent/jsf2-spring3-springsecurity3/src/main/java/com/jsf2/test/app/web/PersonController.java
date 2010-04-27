package com.jsf2.test.app.web;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import com.jsf2.test.app.domain.Person;
import com.jsf2.test.app.repository.PersonRepository;
import com.jsf2.test.app.service.PersonBatchCreateService;
import com.jsf2.test.common.web.LongParameterResolver;
import com.jsf2.test.common.web.Messenger;
import com.jsf2.test.common.web.PageInfo;

/**
 *
 * @author Dimitar Makariev
 */
@ManagedBean("person")
@Scope("request")
public class PersonController implements Serializable {

    private static final long serialVersionUID = 5351248236727878638L;

    @Inject
    public PersonController(PersonRepository personRepository,
            PersonBatchCreateService batchCreate,
            LongParameterResolver parameterResolver,
            Messenger facesMessenger) {
        this.modelRepository = personRepository;
        this.parameterResolver = parameterResolver;
        this.facesMessenger=facesMessenger;
        this.batchCreate = batchCreate;
        this.maxItemsOnPage = 5;
        this.pageIndex = new Long(1);
    }

    public void actionListener() {
    }
    private final PersonRepository modelRepository;
    private final LongParameterResolver parameterResolver;
    private final Messenger facesMessenger;
    private final PersonBatchCreateService batchCreate;

    private final static String MODEL_ID_PARAMETER="personId";

    public String create12Recrods() {
        int numberOfRecords = 12;
        batchCreate.batchCreateRecords(numberOfRecords);
        return "/secured/person/list?faces-redirect=true";
    }

    public String deleteAll() {
        int deletedRecords = batchCreate.deleteAll();
        facesMessenger.addSuccessMessage(" deleted " + deletedRecords + " persons ");
        items = null;
        pageInfo = null;
        return "/secured/person/list";
    }
    private Long pageIndex;
    private int maxItemsOnPage;
    private Person model;
    private List<Person> items;
    private PageInfo pageInfo;

    public String delete() {
        final Long modelId = parameterResolver.resolve(MODEL_ID_PARAMETER);
        modelRepository.delete(modelId);
        return "/secured/person/list?faces-redirect=true";
    }

    public String save() {
        this.model = modelRepository.save(getModel());
        return "/secured/person/detail?faces-redirect=true&"+MODEL_ID_PARAMETER+"=" + getModel().getId();
    }

    public List<Person> getItems() {
        if (false == getPageInfo().getHasItems()) {
            return null;
        }
        if (null == items) {
            items = modelRepository.findAll(getPageInfo().getFirstItemIndex(), getPageInfo().getMaxItemsOnPage());
        }
        return items;
    }

    public PageInfo getPageInfo() {
        if (null == pageInfo) {
            final int itemCount = modelRepository.countAll();
            final long currentPage = this.getPageIndex().longValue();
            pageInfo = new PageInfo(maxItemsOnPage, itemCount, currentPage);
        }
        return pageInfo;
    }

    public Person getModel() {
        if (null != model) {
            return model;
        }

        final Long modelId = parameterResolver.resolve(MODEL_ID_PARAMETER);
        if (null != modelId) {
            model = modelRepository.findById(modelId);
        }
        
        if (null == model) {
            model = new Person();
        }
        return model;
    }

    private Long getPageIndex() {
        final Long resolvedPage = parameterResolver.resolve("page");        
        if (null != resolvedPage) {
            pageIndex = resolvedPage;
        }       
        return pageIndex;
    }

    public boolean getIsNew() {
        return null == getModel().getId();
    }
}
