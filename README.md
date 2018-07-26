# BairesListDemo
Demonstrates a RecyclerView implemented in MVP (Model View Presenter) pattern using Java, Retrofit.

## Setup
Nothing Special. Just Clone it for use.

### LibMaster
> A library specific for the project that contains the customization for an EditText with cleaning icon and border.

> This structure will allow an expansion for the creation of new controls that can be customized.

###  GetCardsListServerDataCWB / Trans_Env
> This is a alternative way to access the WebService. These two files are not been used. if you want to use it make the following change in CardsList.Java

```sh
mPresenter = new CardsListPresenter(new GetCardsListServerDataCWB(this));
```
 
