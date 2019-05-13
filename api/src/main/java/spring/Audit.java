package spring;

public class Audit {
    /**
     * 1. @EnableJpaAuditing trên @Configuration(Thường trên main() của Spring Boot)
     * 2. @EntityListeners(AuditingEntityListener.class) trên @Entity. @EntityListeners chứa các call back
     * 2.1 Với các config trên đã có thể sử dụng @CreatedDate, @LastModifiate
     * 2.2 Để sử dụng: @CreatedBy, @LastModifiedBy cần thay đổi bước 1 : @EnableJpaAuditing(auditorAwareRef="auditorProvider")
     *     Impl AuditorAware Interface, override getCurrentAuditor() function với bean name như trên: auditorProvider
     * 2.3 Với 2.2. Việc setCreatedBy() sẽ bị override. Nếu muốn sử dụng setCreatedBy() có thể customize:
     * -   Customize bằng cách impl JpaAuditingHandler extends AuditingHandler
     * -   Định nghĩa một bean definition bằng cách impl ImportBeanDefinitionRegistrar interface
     * -   Nếu định nghĩa bean definition bằng annotation sẽ không làm việc
     * -   Override registerBeanDefinitions() function
     * -   @Import(JpaAuditingHandlerRegistrar.class) trên @Configuration
     *
     * 3. Tạo bảng _audit với envers
     * 3.1 Chắc chắn đã có dependency envers
     * 3.2 @Audited trên @Entity
     * 3.3 Table với table_audit sẽ được tự động tạo ra với 2 field: REVTYPE(0,1,2-add,update,delete), REV
     *     Table REVINFO sẽ được tự động tạo ra với 2 field: REV, REVTSTMP
     *     rev trong table_audit là khoá ngoại của revinfo
     * 3.4 Customize nếu muốn:
     *     spring.jpa.properties.org.hibernate.envers.audit_table_suffix = _AUD
     *     spring.jpa.properties.org.hibernate.envers.revision_field_name = REVISION_ID
     *     spring.jpa.properties.org.hibernate.envers.revision_type_field_name = REVISION_TYPE
     * */


    /*@Override
    @NotNull
    public Optional<String> getCurrentAuditor() {
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        if (userAuthentication == null || !userAuthentication.isAuthenticated()) {
            return Optional.of("Brother");
        }

        String username = userAuthentication.getUser().getUser().getUserName();
        try {
            return username;
        } catch (Exception ignored) {
            return Optional.of(username);
        }
    }*/


    /*public class JpaAuditingHandler extends AuditingHandler {
        public JpaAuditingHandler(MappingContext<? extends PersistentEntity<?, ?>, ? extends PersistentProperty<?>> mappingContext) {
            super(mappingContext);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object markCreated(Object target) {

            if (target instanceof CustomerAudit) {
                // Store original maker, checker value
                String maker = ((CustomerAudit) target).getMaker();
                String checker = ((CustomerAudit) target).getChecker();

                // Let handler create all annotation need to auditing
                target = super.markCreated(target);

                // Add original value in case they are not empty
                // That's why we need to check empty, because when someone force tor set auditor then they are not empty.
                if (StringUtils.isNotEmpty(maker)) ((CustomerAudit) target).setMaker(maker);
                if (StringUtils.isNotEmpty(checker)) ((CustomerAudit) target).setChecker(checker);
                return target;
            }

            return super.markCreated(target);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object markModified(Object source) {
            if (source instanceof BPMMessageQueue) {
                String updater = ((BPMMessageQueue) source).getChecker();
                LocalDateTime updated = ((BPMMessageQueue) source).getUpdatedDate();

                source = super.markModified(source);

                if (StringUtils.isNotEmpty(updater)) ((BPMMessageQueue) source).setChecker(updater);
                if (updated != null) ((BPMMessageQueue) source).setUpdatedDate(updated);

                return source;
            }

            return super.markModified(source);
        }

        @Override
        @Autowired
        public void setAuditorAware(@Qualifier("springSecurityAuditorAware") AuditorAware<?> auditorAware) {
            super.setAuditorAware(auditorAware);
        }
    }*/


    /*public class JpaAuditingHandlerRegistrar implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            registry.registerBeanDefinition("jpaAuditingHandler", BeanDefinitionBuilder
                    .rootBeanDefinition(JpaAuditingHandler.class)
                    .addConstructorArgReference("jpaMappingContext")
                    .getBeanDefinition());
        }
    }*/

}
